<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统菜单</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 添加角色按钮区域 -->
      <el-row>
        <el-col>
          <el-button type="primary" @click="changeAdd">添加角色</el-button>
        </el-col>
      </el-row>

      <!-- 角色列表区域 -->
      <el-table :data="rolesList" border stripe>
        <!-- 展开列 -->
        <el-table-column type="expand">
          <template v-slot="scope">
            <el-row
              v-for="(item1, index1) in scope.row.children"
              :key="item1.id"
            >
              <!-- 渲染一级权限 -->
              <el-tag
                ><el-col :span="5">{{ item1.authName }}</el-col></el-tag
              >

              <!-- 渲染二级权限 -->
              <el-col :span="19"></el-col>
            </el-row>
            <pre>
              {{ scope.row }}
              </pre
            >
          </template>
        </el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="角色名称" prop="roleName"></el-table-column>
        <el-table-column label="角色描述" prop="roleDescrit"></el-table-column>
        <el-table-column label="操作" width="300px">
          <template v-slot="scope">
            <el-button
              size="mini"
              type="primary"
              icon="el-icon-edit"
              @click="changeEdit(scope.row.id)"
              >编辑</el-button
            >
            <el-button
              size="mini"
              type="danger"
              icon="el-icon-delete"
              @click="delRolesById(scope.row.id)"
              >删除</el-button
            >
            <el-button size="mini" type="warning" icon="el-icon-setting"
              >分配权限</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-card>

    <!-- 添加角色区域 -->
    <el-dialog
      title="添加角色"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addFormClose"
    >
      <el-form
        :model="addForm"
        :rules="editFromRules"
        ref="addFormRef"
        label-width="100px"
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="addForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDescrit">
          <el-input v-model="addForm.roleDescrit"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoles">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑区域 -->
    <el-dialog
      title="修改角色信息"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editDialogClose"
    >
      <el-form
        :model="editForm"
        :rules="editFromRules"
        ref="editFromRef"
        label-width="100px"
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="editForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDescript">
          <el-input v-model="editForm.roleDescrit"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="upRolesById">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getRolesInfo,
  getRolesInfoById,
  upRoles,
  addRoles,
  delRolesById,
} from "../../network/roles";
export default {
  name: "Roles",
  data() {
    return {
      rolesList: [],
      queryInfo: {
        query: "",
        //当前的页数
        pageNum: 1,
        //当前每页显示的数据数
        pageSize: 5,
      },
      total: 0,
      // 控制添加角色区域
      addDialogVisible: false,
      // 控制编辑区域
      editDialogVisible: false,
      //添加表单区域数据源
      addForm: {
        roleName: "",
        roleDescrit: "",
      },
      // 编辑表单区域数据源
      editForm: {},
      //编辑表单较验规则
      editFromRules: {
        roleName: [
          { required: true, message: "请输入角色名称", trigger: "blur" },
        ],
        roleDescrit: [
          { required: true, message: "请输入描述", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getRolesList();
  },
  mounted() {},
  methods: {
    getRolesList() {
      getRolesInfo(this.queryInfo).then((res) => {
        this.rolesList = res;
        this.total = res[0].total;
      });
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize;
      this.getRolesList();
    },
    handleCurrentChange(pageNum) {
      this.queryInfo.pageNum = pageNum;
      this.getRolesList();
    },

    // 控制添加角色显示框隐藏显示
    changeAdd() {
      this.addDialogVisible = !this.addDialogVisible;
    },

    // 关闭添加角色对话框清空校验
    addFormClose() {
      this.$refs.addFormRef.resetFields();
    },
    //添加角色操作事件
    addRoles() {
      //校验
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        addRoles(this.addForm).then((res) => {
          if (res != 0) return this.$message.error("添加用户失败");
          this.$message.success("添加用户成功");
          this.addDialogVisible = false;
          this.getRolesList();
        });
      });
    },

    // 编辑区域显示隐藏
    changeEdit(id) {
      //显示前根据ID获取数据
      getRolesInfoById(id).then((res) => {
        this.editForm = res[0];
      });
      this.editDialogVisible = !this.editDialogVisible;
    },
    // 关闭对话框移除校验
    editDialogClose() {
      this.$refs.editFromRef.resetFields();
    },
    // 编辑框单击确定事件更新数据
    upRolesById() {
      // 触发校验规则
      this.$refs.editFromRef.validate((valid) => {
        if (!valid) return;
        // 发起修改请求
        upRoles(this.editForm).then((res) => {
          if (res != 0) return this.$message.erroe("角色更新失败");
          this.$message.success("角色更新成功");
          // 关闭对话框
          this.editDialogVisible = false;
          this.getRolesList();
        });
      });
    },
    async delRolesById(id) {
      const confirm = await this.$confirm(
        "您正在删除角色信息, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((err) => err);

      if (confirm != "confirm") return this.$message.info("已经取消删除");
      delRolesById(id).then((res) => {
        if (res != 0) return this.$message.error("删除角色失败");
        this.$message.success("删除角色成功");
        this.getRolesList();
      });
    },
  },
};
</script>

<style  scoped>
</style>
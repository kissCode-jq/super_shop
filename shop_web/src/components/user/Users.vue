<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统菜单</el-breadcrumb-item>
      <el-breadcrumb-item>人员维护</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区域 -->
    <el-card class="box-card">
      <!-- 搜索与添加区域 -->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input
            placeholder="请输入内容"
            v-model="queryInfo.query"
            clearable
            @clear="getUserList"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="getUserList"
            ></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true"
            >添加用戶</el-button
          >
        </el-col>
      </el-row>
      <!-- 用户列表区 -->
      <el-table :data="userlist" style="width: 100%" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="username" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="userage" label="年龄" width="180">
        </el-table-column>
        <el-table-column prop="loginname" label="账号"> </el-table-column>
        <el-table-column label="状态">
          <template v-slot="scope">
            <el-switch
              v-model="scope.row.status"
              @change="userStateChanged(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template v-slot="scope">
            <!-- 修改按钮 -->
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEdiDialog(scope.row.id)"
            ></el-button>
            <!-- 删除按钮 -->
           
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeUserById(scope.row.id)"
            ></el-button>
           
            <!-- 分配角色按钮 -->
            <el-tooltip
              effect="dark"
              content="分配角色"
              placement="top"
              :enterable="false"
            >
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
              ></el-button>
            </el-tooltip>
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

    <!-- 添加用户的对话框 -->
    <el-dialog
      title="添加用户"
      :visible.sync="addDialogVisible"
      width="50%"
      @close="addDialogClose"
    >
      <!-- 内容主体区域 -->
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="70px"
      >
        <el-form-item label="姓名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="addForm.age"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="loginname">
          <el-input v-model="addForm.loginname"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="editDialogVisable" width="50%" @close="editDialogClose">
      <!-- 内容主体区域 -->
      <el-form
        :model="editForm"
        :rules="editFormRules"
        ref="editFormRef"
        label-width="70px"
      >
       <el-form-item label="姓名">
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="年龄" >
          <el-input v-model="editForm.age"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="loginname">
          <el-input v-model="editForm.loginname"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
     
        
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisable = false">取 消</el-button>
        <el-button type="primary" @click="updateUser"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getUserInfo, upUserStatus, addUser,getUserBean,upUser,delUser } from "../../network/user";
export default {
  name: "Users",
  data() {
    //自定义校验规则
    var checkPassword = (rule, value, cb) => {
      if (value == "111") {
        cb(new Error("请输入合法的密码"));
      } else {
        return cb();
      }
    };

    return {
      //获取用户列表的参数对象
      queryInfo: {
        query: "",
        //当前的页数
        pageNum: 1,
        //当前每页显示的数据数
        pagesize: 5,
      },
      userlist: [],
      total: 0,
      //控制添加对话框的显示和隐藏
      addDialogVisible: false,
      addForm: {
        username: "",
        age: "",
        loginname: "",
        password: "",
      },
      //添加表单的验证规则对象
      addFormRules: {
        username: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            min: 1,
            max: 8,
            message: "长度在 1 到 8 个字符",
            trigger: "blur",
          },
        ],
        age: [
          { required: true, message: "请输入年龄", trigger: "blur" },
          {
            min: 1,
            max: 3,
            message: "长度在 1 到 3 个字符",
            trigger: "blur",
          },
        ],
        loginname: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { validator: checkPassword, trigger: "blur" },
        ],
      },
      //控制修改显示对话框的修改
      editDialogVisable: false,
      editForm:{
        username: "",
        age: "",
        loginname: "",
        password: "",
        id:""
      },
      editFormRules:{
          age: [
          { required: true, message: "请输入年龄", trigger: "blur" },
          {
            min: 1,
            max: 3,
            message: "长度在 1 到 3 个字符",
            trigger: "blur",
          },
        ],
        loginname: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],

        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { validator: checkPassword, trigger: "blur" },
        ],
      }
    };
  },
  created() {
    this.getUserList();
  },
  mounted() {},
  methods: {
    getUserList() {
      getUserInfo(this.queryInfo).then((res) => {
        this.userlist = res;
        this.total = this.userlist[0].total;
      });
    },
    //监听pagesize改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getUserList();
    },
    //监听页码值改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage;
      this.getUserList();
    },
    //改变用户状态
    userStateChanged(user) {
      upUserStatus(user).then((res) => {
        if (res != 0) {
          user.status = !user.status;
          this.$message.error("修改状态失败");
        }
        this.$message.success("修改状态成功");
      });
    },
    // 监听添加用户对话框的关闭事件
    addDialogClose() {
      this.$refs.addFormRef.resetFields();
    },
    // 点击按钮添加新用户
    addUser() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return;
        //可以发起用户的网络请求
        addUser(this.addForm).then((res) => {
          if (res != 0) return this.$message.error("新增用户失败");
          this.$message.success("新增用户成功");
          this.addDialogVisible = false;
          this.getUserList();
        });
      });
    },
    // 展示编辑用户的对话框
    showEdiDialog(id) {
      this.editDialogVisable=true;
      //根据ID获取用户信息
      getUserBean(id).then(res=>{
        this.editForm.username=res[0].username;
        this.editForm.age=res[0].userage;
        this.editForm.loginname=res[0].loginname;
        this.editForm.password=res[0].userpassword;
        this.editForm.id=res[0].id;
      })
    },
     // 监听添加用户对话框的关闭事件
      editDialogClose(){
        this.$refs.editFormRef.resetFields();
      },
      //更新用户信息
      updateUser(){
        this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return;
        upUser(this.editForm).then(res=>{
          if (res != 0) return this.$message.error("更新用户失败");
          this.$message.success("更新用户成功");
          this.editDialogVisable = false;
          this.getUserList();
        })

        });
      },
      // 根据ID删除用户
      async removeUserById(id){
        const confirmResult=await this.$confirm('您正在删除用户信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err=> err)
        if(confirmResult!="confirm"){
          return this.$message.info("已经取消删除");
        }

        delUser(id).then(res=>{
          if (res != 0) return this.$message.error("删除用户失败");
          this.$message.success("删除用户成功");
          this.getUserList();
        })



      }
      
  },
};
</script>

<style  scoped>
.el-table {
  font-size: 12px;
  margin-top: 10px;
}
</style>
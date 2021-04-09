<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统菜单</el-breadcrumb-item>
      <el-breadcrumb-item>参数管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-alert show-icon closable title="这是参数列表" type="warning">
      </el-alert>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="动态参数" name="manry">
          <el-button type="primary" @click="addAttr">添加参数</el-button>
          <el-table :data="manryData" style="width: 100%" border stripe>
            <el-table-column type="expand">
              <!-- 循环渲染tag标签 -->
              <template v-slot="scope">
                <el-tag
                  class="tagClass"
                  v-for="(item, index) in scope.row.attrMemo"
                  :key="index"
                  closable
                  @close="closeTag(index, scope.row)"
                >
                  {{ item }}
                </el-tag>

                <el-input
                  class="input-new-tag"
                  v-if="scope.row.inputVisible"
                  v-model="scope.row.inputValue"
                  ref="saveTagInput"
                  size="small"
                  @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)"
                >
                </el-input>
                <el-button
                  v-else
                  class="button-new-tag"
                  size="small"
                  @click="showInput(scope.row)"
                  >+ New Tag</el-button@click="deleteAttr(scope.row.id)"
                >
              </template>
            </el-table-column>
            <el-table-column type="index"></el-table-column>
            <el-table-column prop="attrName" label="参数名称"></el-table-column>
            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-edit"
                  @click="showEditDialog(scope.row.id)"
                  >编辑</el-button
                >
                <el-button size="mini" type="danger" icon="el-icon-delete"
                @click="deleteAttr(scope.row.id)"  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="静态属性" name="only">
          <el-button type="primary" @click="addAttr">添加属性</el-button>
          <el-table :data="onlyData" style="width: 100%" border stripe>
            <el-table-column type="expand">
              <template v-slot="scope">
                <el-tag
                  v-for="(item, index) in scope.row.attrMemo"
                  :key="index"
                  closable
                  @close="closeTag(index, scope.row)"
                >
                  {{ item }}
                </el-tag>
                <el-input
                  class="input-new-tag"
                  v-if="scope.row.inputVisible"
                  v-model="scope.row.inputValue"
                  ref="saveTagInput"
                  size="small"
                  @keyup.enter.native="handleInputConfirm(scope.row)"
                  @blur="handleInputConfirm(scope.row)"
                >
                </el-input>
                <el-button
                  v-else
                  class="button-new-tag"
                  size="small"
                  @click="showInput(scope.row)"
                  >+ New Tag</el-button
                >
              </template>
            </el-table-column>
            <el-table-column type="index"> </el-table-column>
            <el-table-column prop="attrName" label="属性名称">
            </el-table-column>
            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-edit"
                  @click="showEditDialog(scope.row.id)"
                  >编辑</el-button
                >
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="deleteAttr(scope.row.id)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 新增对话框区域 -->
    <el-dialog
      :title="'新增' + titleText"
      :visible.sync="addAttrdialogVisible"
      width="50%"
      @close="addFromClose"
    >
      <el-form
        :model="addForm"
        :rules="addFromRules"
        ref="addFormRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item :label="titleText" prop="attrName">
          <el-input v-model="addForm.attrName"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAttrdialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAttrInfo">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改对话框区域 -->
    <el-dialog
      :title="'修改' + titleText"
      :visible.sync="editDialogVisible"
      width="50%"
      @close="editFormClose"
    >
      <el-form
        :model="editForm"
        :rules="addFromRules"
        ref="editFormRef"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item :label="titleText" prop="attrName">
          <el-input v-model="editForm.attrName"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="upAttr">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getAttrListInfo,
  upAttrsById,
  addAttrs,
  getAttrById,
  upAttrInfoById,
  deleteAttrById
} from "../../network/attrs";
export default {
  name: "Attrs",
  data() {
    return {
      //活跃状态的列表
      activeName: "manry",
      //参数列表数据
      manryData: [],
      onlyData: [],
      // 控制添加dialog显示隐藏按钮
      addAttrdialogVisible: false,
      // 新增表单数据
      addForm: { attrName: "" },
      addFromRules: {
        attrName: [{ required: true, message: "请输入数据", trigger: "blur" }],
      },
      // 修改对话框显示隐藏
      editDialogVisible: false,
      // 修改对话框参数列表
      editForm: [],
    };
  },
  created() {
    this.getAttrsInfo();
  },
  computed: {
    // 动态计算标题的文本
    titleText() {
      if (this.activeName === "manry") {
        return "动态参数";
      } else {
        return "静态属性";
      }
    },
  },
  mounted() {},
  methods: {
    //获取参数列表
    getAttrsInfo() {
      getAttrListInfo(this.activeName).then((res) => {
        res.forEach((item) => {
          item.attrMemo = item.attrMemo ? item.attrMemo.split(",") : [];
          // 控制文本框的显示与隐藏
          item.inputVisible = false;
          //文本框输入的值
          item.inputValue = "";
        });
        if (this.activeName == "manry") {
          //将配置参数改为数组形式
          this.manryData = res;
        } else {
          this.onlyData = res;
        }
      });
    },
    handleClick() {
      this.getAttrsInfo();
    },
    // 文本框失去焦点或按下Enter健
    handleInputConfirm(row) {
      if (row.inputValue.trim().length === 0) {
        row.inputValue = "";
        row.inputVisible = false;
        return;
      }

      row.attrMemo.push(row.inputValue.trim());
      row.inputVisible = false;
      row.inputValue = "";

      //如果没有return则证明输入了内容,需要做提交数据处理
      upAttrsById(row.id, row.attrMemo.join(","), this.activeName).then(
        (res) => {
          if (res != 0) return this.$message.error("更新数据失败");
          this.$message.success("更新数据成功");
        }
      );
    },
    // 点击按钮,展示文本输入框
    showInput(row) {
      row.inputVisible = true;
      //获取文本框焦点
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    //删除对应的参数可选项
    closeTag(index, row) {
      row.attrMemo.splice(index, 1);

      //如果没有return则证明输入了内容,需要做提交数据处理
      upAttrsById(row.id, row.attrMemo.join(","), this.activeName).then(
        (res) => {
          if (res != 0) return this.$message.error("更新数据失败");
          this.$message.success("更新数据成功");
        }
      );
    },
    // 新增参数方法
    addAttr() {
      this.addAttrdialogVisible = !this.addAttrdialogVisible;
    },
    // 关闭添加列表
    addFromClose() {
      // 清空添加参数列表
      this.$refs.addFormRef.resetFields();
    },
    // 新增参数信息
    addAttrInfo() {
      //表单验证
      this.$refs.addFormRef.validate((valid) => {
        if (!valid) return;
        //发起请求
        addAttrs(this.activeName, this.addForm.attrName).then((res) => {
          if (res != 0) return this.$message.error("新增参数失败");
          this.$message.success("新增参数成功");
          this.addAttrdialogVisible = false;
          this.getAttrsInfo();
        });
      });
    },
    // 控制修改对话框显示隐藏
    showEditDialog(id) {
      //根据ID获取参数数据
      getAttrById(this.activeName, id).then((res) => {
        this.editForm = res[0];
        console.log(res);
      });
      this.editDialogVisible = !this.editDialogVisible;
    },
    // 关闭对话框,移除校验
    editFormClose() {
      this.$refs.editFormRef.resetFields();
    },
    upAttr() {
      //修改数据前表单验证
      upAttrInfoById(this.activeName, this.editForm).then((res) => {
        if (res != 0) return this.$message.error("修改参数失败");
        this.$message.success("修改参数成功");
        this.editDialogVisible = false;
        this.getAttrsInfo();
      });
    },
    async deleteAttr(id) {
      const confirm = await this.$confirm(
        "你正在删除该条参数, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).catch((err) => err);
      if (confirm != "confirm") return this.$message.info("您已经取消了删除");
      deleteAttrById(this.activeName,id).then((res) => {
        this.getAttrsInfo();
      });
    },
  },
};
</script>

<style  scoped>
.el-tabs {
  margin: 15px 0;
}

.el-tag {
  margin: 10px;
}

.input-new-tag {
  width: 120px;
}
</style>
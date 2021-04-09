<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统菜单</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图区域 -->
    <el-card class="box-card" >
      <el-table :data="rightsList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="权限名称" prop="rightName"></el-table-column>
        <el-table-column label="权限路径" prop="rightPath"></el-table-column>
        <el-table-column label="等级" prop="rightGrand">
           <template v-slot="scope">
             <el-tag v-if="scope.row.rightGrand==='1'" type="success">一级</el-tag>
             <el-tag v-else-if="scope.row.rightGrand==='2'" type="info">二级</el-tag>
             <el-tag v-else="scope.row.rightGrand==='3'" type="warning">三级</el-tag>
           </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { getRightsInfo } from "../../network/rights";

export default {
  name: "Right",
  data() {
    return {
      rightsList: [],
    };
  },
  created() {
    this.getRightsList();
  },
  mounted() {},
  methods: {
    getRightsList() {
      getRightsInfo().then((res) => {
        this.rightsList = res;
        console.log(this.rightsList);
      });
    },
  },
};
</script>

<style  scoped>
</style>
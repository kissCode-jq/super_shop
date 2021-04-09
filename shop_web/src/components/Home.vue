<template>
  <el-container class="home-container">
    <!-- 头部区域 -->
    <el-header>
      <span>电商后台管理系统</span>
      <el-button type="info" @click="logout()">退出</el-button>
    </el-header>
    <!-- 页面主体区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px' :'200px'">
        <!-- 侧边栏菜单区域 -->
         <el-menu
      background-color="#333744"
      text-color="#fff"
      active-text-color="#409bff"
      :unique-opened="true"
      :collapse="isCollapse"
      :collapse-transition="false"
      :router="true"
      :default-active="activePath">
       <!-- 侧边栏折叠按钮 -->
      <div class="toggle-button"  @click="toggleCollapse">|||</div> 
      <!-- 一级菜单 -->
      <el-submenu :index="menu.id+'' " v-for="menu in menuList" :key="menu.id" >
        <!-- 一级菜单的模板区 -->
        <template slot="title">
           <!-- 图标 -->
          <i :class="iconObj[menu.id]"></i>
          <!-- 文本 -->
          <span>{{menu.authName}}</span>
        </template>

          <!-- 二级菜单 -->
          <el-menu-item :index="'/'+subItem.path" v-for="subItem in menu.children" :key="subItem.id" @click="saveNacState('/'+subItem.path)">
            <template slot="title">
             <i class="el-icon-menu"></i>
          <!-- 文本 -->
             <span>{{subItem.authName}}</span>
            </template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </el-col>
</el-row>
</el-aside>
      <!-- 右侧主体 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
       </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { getMenuInfo } from "../network/home";

export default {
  name: "Home",

  data() {
    return {
      //左侧菜单数据
      menuList: [],
      iconObj: {
        111: "el-icon-s-tools",
        222: "el-icon-s-goods",
      },
      //是否折叠
      isCollapse: false,
      //被激活的链接地址
      activePath:''
    };
  },
  created() {
    this.getMenu();
    this.activePath=window.sessionStorage.getItem("activePath");
  },
  mounted() {},
  methods: {
    //退出
    logout() {
      //清楚token
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    getMenu() {
      //获取侧边栏数据
      getMenuInfo().then((res) => {
        this.menuList = res;
      });
    },
    toggleCollapse() {
      //点击按钮这点侧边栏
      this.isCollapse = !this.isCollapse;
      
    },
    //保存连接的激活状态
    saveNacState(activePath){
      window.sessionStorage.setItem("activePath",activePath);
      this.activePath=activePath;
      
    }
  },
};
</script>

<style  scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 20px;
  color: #fff;
}
.el-aside {
  background-color: #333744;
}
.el-main {
  background-color: #eaedf1;
}
.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
.el-menu{
  border-right: 0;
}
</style>
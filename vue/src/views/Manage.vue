<template>
  <el-container style="min-height: 100vh">
    <el-aside
      :width="sideWidth + 'px'"
      style="
        background-color: rgb(238, 241, 246);
        box-shadow: 2px 0 6px rgb(0 21 41 / 35%);
      "
    >
      <Aside
        :isCollapse="isCollapse"
        :logoTextShow="logoTextShow"
        
      ></Aside>
    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #cccccc">
        <Header
          :collapseBtnClass="collapseBtnClass"
          :collapse="collapse"
          :user="user"
        ></Header>
      </el-header>

      <el-main>
        <router-view @refreshUesr="getUser"></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "../components/Aside.vue";
import Header from "../components/Header.vue";
export default {
  name: "Manage",
  data() {
    return {
      collapseBtnClass: "el-icon-s-fold",
      // false展开 true折叠
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      user: {},
    };
  },
  components: { Aside, Header },
  created() {
    // 从后台获取最新User数据
    this.getUser();
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        // 收缩
        this.sideWidth = 64;
        this.collapseBtnClass = "el-icon-s-unfold";
        this.logoTextShow = !this.logoTextShow;
      } else {
        // 展开
        this.sideWidth = 200;
        this.collapseBtnClass = "el-icon-s-fold";
        this.logoTextShow = !this.logoTextShow;
      }
    },
    getUser() {
      let username = localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user")).username
        : "";
      // 从后台获取User数据
      this.request.get("/user/username/" + username).then((res) => {
        // 重新赋值后台的最新user数据
        this.user = res.data;
      });
    },
  },
};
</script>

<style>
</style>
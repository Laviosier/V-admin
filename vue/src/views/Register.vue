<template>
  <div class="wrapper">
    <div
      style="
        margin: 100px auto;
        background-color: #fff;
        width: 350px;
        height: 400px;
        padding: 20px;
        border-radius: 10px;
      "
    >
      <div style="margin: 20px 0; text-align: center; font-size: 24px">
        <b>注 册</b>
      </div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input
            size="medium"
            style="margin: 5px 0"
            prefix-icon="el-icon-user"
            v-model="user.username"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            size="medium"
            style="margin: 5px 0"
            prefix-icon="el-icon-lock"
            show-password
            v-model="user.password"
            placeholder="请输入密码"
            @keyup.enter.native="login"
          ></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input
            size="medium"
            style="margin: 5px 0"
            prefix-icon="el-icon-lock"
            show-password
            v-model="user.confirmPassword"
            placeholder="请再次输入密码"
            @keyup.enter.native="register"
          ></el-input>
        </el-form-item>
        <el-form-item style="margin: 5px 0; text-align: right">
          <el-button
            type="primary"
            size="small"
            autocomplete="off"
            @click="register"
            >确定</el-button
          >
          <el-button
            type="warning"
            size="small"
            autocomplete="off"
            @click="$router.push('/login')"
            >返回登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      user: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 10, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
        confirmPassword: [
          { required: true, message: "请再次输入密码", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    register() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          if (this.user.password !== this.user.confirmPassword) {
            this.$message.error("两次输入密码不一致");
            return false;
          }
          // 表单校验合法
          this.request.post("/user/register", this.user).then((res) => {
            if (res.code === "200") {
              this.$router.push("/login");
              this.$message.success("注册成功");
            } else {
              this.$message.error(res.msg);
            }
          });
        } else {
          return false;
        }
      });
    },
  },
};
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
  overflow: hidden;
}
</style>

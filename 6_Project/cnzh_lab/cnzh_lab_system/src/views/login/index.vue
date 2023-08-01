<template>
  <div class="login-container">
    <div class="login-head">
      <div class="line-left">
        <img src="../../assets/login/line_l.png" alt="line-left">
      </div>
      <div class="login-head-logo">
        <img src="../../assets/login/logo.png" alt="logo">
      </div>
      <h1 class="login-head-title">实验室管理系统</h1>
      <div class="line-right">
        <img src="../../assets/login/line_r.png" alt="line-right">
      </div>
    </div>
    <div class="login-info">
      <div class="login-info-bg03">
          <img src="../../assets/login/bg03.png" alt="bg03"/>
      </div>
      <div class="login-border">
        <div class="login-main">
            <el-form autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
              <el-form-item prop="username">
                  <el-input size="medium" type="text" v-model="loginForm.username" autoComplete="on" placeholder="/ 手机号、邮箱">
                    <i slot="prefix" class="el-icon-mobile-phone"></i>
                  </el-input>
              </el-form-item>
              <el-form-item prop="password">
                  <el-input size="medium" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on" placeholder="/ 密码">
                    <i slot="prefix" class="el-icon-view"></i>
                  </el-input>
                  <span class="show-pwd" @click="showPwd"><svg-icon icon-class="eye" /></span>
              </el-form-item>
              <el-form-item>
                  <el-button size="medium" :loading="loading" @click.native.prevent="handleLogin" class="login-submit" type="danger">登陆</el-button>
              </el-form-item>
            </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { isvalidUsername } from '@/utils/validate'

export default {
  name: 'login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: 'admin',
        password: 'admin'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
      },
      loading: false,
      pwdType: 'password'
    }
  },
  methods: {
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({ path: '/' })
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>


<style lang="scss">
.login-container {
  display: flex;
  align-items: center;
  justify-content: space-around;
  background-image: url("../../assets/login/bg02.png");
  background-size: cover;
  position: relative;
  height: 100%;
}
.login-container::before {
  z-index: -999;
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/login/bg01.png");
  background-size: cover;
}
.login-head {
  width: 100%;
  height: 120px;
  position: absolute;
  top: 40px;
  left: 0px;
  display: flex;
  justify-content: center;
  align-items:center;
}
.line-left {
  padding:0 60px;
}
.login-head-logo {
  padding:0 30px;
  img {
    width: 70px;
    height: 70px;
  }
}
.login-head-title {
  font-size:50px;
  color: #fff;
}
.line-right {
  padding:0 60px;
}

.login-info {
  width: 100%;
  position: absolute;
  top: 300px;
  padding-left: 120px;
  padding-right: 120px;
}
.login-info-bg03 {
  position: absolute;
  img {
     width: 660px;
     height: 360px;
  }
}
.login-border {
  display: flex;
  position: absolute;
  width: 400px;
  height: 350px;
  top: 0px;
  right: 120px;
  justify-content: center;
  flex-direction: column;
  padding: 30px 50px 25px 50px;
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 1px 1px 2px #eee;
}
.login-main {
  border-radius: 3px;
  box-sizing: border-box;
  background-color: #fff;
  i {
    margin-left: 5px;
  }
}
.login-submit {
  margin-top: 20px;
  width: 100%;
  border-radius: 5px;
}
.show-pwd {
    position: absolute;
    right: 10px;
    top: 1px;
    font-size: 16px;
    cursor: pointer;
    user-select: none;
  }

</style>

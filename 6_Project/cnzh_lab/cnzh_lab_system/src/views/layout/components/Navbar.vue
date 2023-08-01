<template>
  <el-menu class="navbar" mode="horizontal">
    <img class="navbar-logo" src="../../../assets/login/logo.png" alt="navbar-logo">
    <span class="navbar-line">|</span>
    <breadcrumb></breadcrumb>
    <div class="navbar-message">你好，欢迎使用本系统 今天是2018年11月1日星期一</div>
    <div class="avatar-container" trigger="click">
      <div class="avatar-wrapper">
        <img class="user-avatar" :src="avatar+'?imageView2/1/w/80/h/80'">
        <span class="user-name">张三</span>
        <el-popover
          placement="bottom"
          width="160"
          v-model="visible">
          <p>确定要退出吗？</p>
          <div style="text-align: right; margin: 0">
            <el-button type="danger" size="mini" @click="logout" >确定</el-button>
            <el-button size="mini" type="text" @click="visible = false">取消</el-button>
          </div>
          <span slot="reference">
            <img class="user-logout" src="../../../assets/icon/logout.png" title="退出">
          </span>
        </el-popover>
      </div>
    </div>
  </el-menu>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'

export default {
  components: {
    Breadcrumb
  },
  data() {
    return {
      visible: false
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  methods: {
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
  height: 50px;
  line-height: 50px;
  border-radius: 0px !important;
  .navbar-logo {
    width: 50px;
    height: 50px;
    padding: 10px;
  }
  .navbar-line {
    position: absolute;
    top: -1px;
  }
  .navbar-message {
    display: inline;
    position: absolute;
    margin: 0 400px;
  }
  .screenfull {
    position: absolute;
    right: 90px;
    top: 16px;
    color: red;
  }
  .avatar-container {
    height: 50px;
    display: inline-block;
    position: absolute;
    right: 65px;
    .avatar-wrapper {
      margin-top: 5px;
      position: relative;
      .user-avatar {
        width: 36px;
        height: 36px;
        margin-top: 3px;
        border-radius: 50%;
        margin-right: 35px;
      }
      .user-name {
        position: absolute;
        top: -3px;
        right: -20px;
        font-size: 14px;
        margin-right: 20px;
      }
      .user-logout {
        cursor: pointer;
        width: 25px;
        height: 25px;
        position: absolute;
        top: 10px;
        right: -50px;
      }
    }
  }
}
</style>


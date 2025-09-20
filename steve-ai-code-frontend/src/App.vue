<script setup lang="ts">
import BasicLayout from '@/layouts/BasicLayout.vue'

import zhCN from 'ant-design-vue/es/locale/zh_CN'
import { useLoginUserStore } from '@/stores/loginUser.ts'

const defaultLocale = zhCN;

const loginUserStore = useLoginUserStore();
loginUserStore.fetchLoginUser();


</script>

<template>
  <ConfigProvider :locale="defaultLocale">
    <div id="app">
      <BasicLayout />
    </div>
  </ConfigProvider>
</template>

<style>
</style>

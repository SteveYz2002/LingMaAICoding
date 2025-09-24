import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { ConfigProvider} from 'ant-design-vue'

import App from './App.vue'
import router from './router'
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';
import 'dayjs/locale/en';

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'

import '@/access'

const app = createApp(App)

const defaultLocale = zhCN;
dayjs.locale(defaultLocale.locale);

app.use(createPinia())
app.use(router)
app.use(Antd)


app.component('ConfigProvider', ConfigProvider)
app.mount('#app')

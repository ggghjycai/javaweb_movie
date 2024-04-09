import { createRouter, createWebHashHistory } from "vue-router";
import Index from "@/components/Index.vue";
import Rank from "@/components/Rank.vue";
import Search from "@/components/Search.vue"
import Detail from "@/components/Detail.vue";
import User from "@/components/User.vue";
import Register from "@/components/Register.vue";
import Login from "@/components/Login.vue";
import Manage from"@/components/Manage.vue"

const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:"/",
            name:"Index",
            component:Index
        },
        {
            path:"/rank",
            name:"Rank",
            component:Rank
        },
        {
            path:"/search",
            name:"Search",
            component:Search
        },
        {
            path:"/detail",
            name:"Detail",
            component:Detail
        },
        {
            path:"/user",
            name:"User",
            component:User
        },
        {
            path:"/register",
            name:"Register",
            component:Register
        },
        {
            path:"/login",
            name:"Login",
            component:Login
        },
        {
            path:"/manage",
            name:"Manage",
            component:Manage
        }
    ]
});

export default router;


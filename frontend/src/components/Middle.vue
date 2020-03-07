<template>
    <div class="middle">
        <aside>
            <SidebarPost v-for="post in viewPosts" :post="post" :key="post.id"/>
        </aside>
        <main>
            <Index v-if="page === 'Index'" :posts="posts" :users="users"/>
            <Users v-if="page === 'Users'" :users="users"/>
            <AddPost v-if="page === 'Add Post'" :user="user"/>
            <Post v-if="page === 'Post'" :post="posts[this.id]" :user="user"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
        </main>
    </div>
</template>
<script>
    import Index from './middle/Index';
    import Users from './middle/Users';
    import Enter from './middle/Enter';
    import Register from './middle/Register';
    import SidebarPost from './SidebarPost';
    import AddPost from "./middle/AddPost";
    import Post from "./middle/Post";

    export default {
        name: "Middle",
        props: ["user", "posts", "users"],
        data: function () {
            return {
                page: "Index"
            }
        },
        computed: {
            viewPosts: function () {
                return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
            }
        },
        components: {
            Index,
            AddPost,
            Post,
            Users,
            Enter,
            Register,
            SidebarPost
        }, beforeCreate() {
            this.$root.$on("onChangePage", (page, id) => {
                if (page === 'Post') {
                    this.id = id;
                }
                this.page = page;
            });
        }
    }
</script>

<style scoped>

</style>

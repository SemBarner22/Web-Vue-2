<template>
    <div class="posts">
        <div v-for="post in reversedPosts" v-bind:key="post.id">
            <article>
                <div class="title">
<!--                    <a v-bind:href="'#page=post&id=' + post.id" @click="changePage('Post', post.id)">-->
                        {{post.title}}
<!--                    </a>-->
                </div>
                <div class="information">By {{post.user.name}}</div>
                <div class="body">
                    {{post.text}}
                </div>
                <div class="footer">
                    <div class="left">
                        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        N/A
                        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                        N/A
                    </div>
                </div>
            </article>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Index",
        props: ["posts", "users"],
        methods: {
            getAuthor: function(id) {
                this.users.forEach(function(item) {
                    if (item.id === id) {
                        return item;
                    }
                })
            }
        },
        computed: {
            reversedPosts: function() {
                function cmp(a, b) {
                    return b.id - a.id;
                }
                return Object.values(this.posts).sort(cmp);
            }
        }
    }
</script>

<style scoped>

</style>

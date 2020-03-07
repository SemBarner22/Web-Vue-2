<template>
    <div class="post">
        <article>
            <div class="title">{{post.title}}</div>
            <div class="information">By {{users[post.userId].name}}</div>
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
                    {{comments.length}}
                </div>
            </div>
        </article>
        <div class="comment" v-for="comment in comments" v-bind:key="comment.id">
            <div class="information">{{users[comment.userId].login}}</div>
            <div class="text">{{comment.text}}</div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "Index",
        props: ['post', 'user'],
        methods: {
            comments: function() {
                axios.get("/api/1/comments", {params: {
                    postId: this.post.id
                }}).then(response => {
                    return response['data']
                })
            }
        }
    }
</script>

<style scoped>

</style>

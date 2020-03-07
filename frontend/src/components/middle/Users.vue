<template>
    <div class="users datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Name</th>
                <th>Admin</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <tr v-if="users.size === 0" class="noData">
                <td colspan="5">
                    No data
                </td>
            </tr>
            <tr v-else v-for="viewUser in users" v-bind:key="viewUser.id">
                <td class="id">{{viewUser.id}}</td>
                <td class="login">{{viewUser.login}}</td>
                <td class="name">{{viewUser.name}}</td>
                <td v-if="viewUser.admin" class="admin">✔</td>
                <td v-else class="admin"/>
                <td class="creationTime">{{viewUser.creationTime}}</td>
            </tr>
        </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name: 'Users',
        props: ["users"]
    }
</script>

<style scoped>

</style>
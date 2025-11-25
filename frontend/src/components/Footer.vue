<script setup>
const currentYear = new Date().getFullYear();
</script>

<template>
    <footer class="py-5">
        <div class="container"> 
            <div>&copy; {{currentYear}}. Example. All rights reserved.</div>
        </div>
    </footer>
</template>
<!-- .container : 템플릿의 핵심 요소를 감싸는 역할 -->
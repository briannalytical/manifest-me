<script setup lang="ts">
import TitleHeader from '@/components/TitleHeader.vue'
</script>

<template>
  <TitleHeader :is-logged-in="true" />

  <main class="about">
    <h1>Title</h1>
    <p>Lorem Ipsum.</p>
  </main>
</template>

<style scoped>
.about {
  max-width: 720px;
  margin: 0 auto;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}
</style>

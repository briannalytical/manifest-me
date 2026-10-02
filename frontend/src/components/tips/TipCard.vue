<script setup lang="ts">
import type { Tip } from '@/types/tip'

defineProps<{
  tip: Tip
}>()
</script>

<template>
  <article class="tips-card">
    <h2>{{ tip.title }}</h2>
    <p>{{ tip.body }}</p>
  </article>
</template>

<style scoped>
.tips-card {
  padding: 2rem;
  background: #fff;
  border: 1px solid #ddd;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
</style>

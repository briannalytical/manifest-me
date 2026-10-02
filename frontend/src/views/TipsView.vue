<script setup lang="ts">
import TitleHeader from '@/components/TitleHeader.vue'
import TipsCarousel from '@/components/tips/TipsCarousel.vue'
import type { Tip } from '@/types/tip'

const tips: Tip[] = [
  {
    id: 1,
    title: 'Tip 1',
    body: 'Lorem Ipsum.',
    displayOrder: 1,
  },
  {
    id: 2,
    title: 'Tip 2',
    body: 'Lorem Ipsum.',
    displayOrder: 2,
  },
  {
    id: 3,
    title: 'Tip 3',
    body: 'Lorem Ipsum.',
    displayOrder: 3,
  },
  {
    id: 4,
    title: 'Tip 4',
    body: 'Lorem Ipsum.',
    displayOrder: 4,
  },
  {
    id: 5,
    title: 'Tip 5',
    body: 'Lorem Ipsum.',
    displayOrder: 5,
  },
]
</script>

<template>
  <TitleHeader :is-logged-in="true" />

  <main class="tips">
    <h1>Job search tips</h1>
    <TipsCarousel :tips="tips" />
  </main>
</template>

<style scoped>
.tips {
  max-width: 720px;
  margin: 0 auto;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;
}
</style>

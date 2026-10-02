<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import type { Tip } from '@/types/tip'
import TipsCard from '@/components/tips/TipCard.vue'

const props = defineProps<{
  tips: Tip[]
}>()

const VISIBLE_DEPTH = 3

const currentIndex = ref(0)

function next() {
  if (!props.tips.length) return
  currentIndex.value = (currentIndex.value + 1) % props.tips.length
}

function previous() {
  if (!props.tips.length) return
  currentIndex.value = (currentIndex.value - 1 + props.tips.length) % props.tips.length
}

function cardStyle(index: number) {
  const position = (index - currentIndex.value + props.tips.length) % props.tips.length
  const depth = Math.min(position, VISIBLE_DEPTH)
  return {
    zIndex: props.tips.length - position,
    transform: `translateY(${depth * -12}px) scale(${1 - depth * 0.05})`,
    opacity: position > VISIBLE_DEPTH ? 0 : 1,
  }
}

function handleKey(event: KeyboardEvent) {
  if (event.key === 'ArrowRight') next()
  if (event.key === 'ArrowLeft') previous()
}

onMounted(() => window.addEventListener('keydown', handleKey))
onUnmounted(() => window.removeEventListener('keydown', handleKey))
</script>

<template>
  <div class="tips-carousel">
    <p v-if="!tips.length">No tips yet.</p>

    <template v-else>
      <div class="stack">
        <TipsCard
          v-for="(tip, index) in tips"
          :key="tip.id"
          :tip="tip"
          class="stack-card"
          :style="cardStyle(index)"
        />
      </div>

      <div class="controls">
        <button class="control" @click="previous">Previous</button>
        <span class="counter">{{ currentIndex + 1 }} of {{ tips.length }}</span>
        <button class="control" @click="next">Next</button>
      </div>
    </template>
  </div>
</template>

<style scoped>
.tips-carousel {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;
  width: 100%;
}

.stack {
  position: relative;
  width: 100%;
  max-width: 480px;
  height: 320px;
  margin-top: 48px;
}

.stack-card {
  position: absolute;
  inset: 0;
  transform-origin: top center;
  transition:
    transform 0.4s ease,
    opacity 0.4s ease;
}

.controls {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.control {
  padding: 0.5rem 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #fff;
  font: inherit;
  cursor: pointer;
}

.counter {
  min-width: 5rem;
  text-align: center;
}
</style>

<script setup lang="ts">
withDefaults(
  defineProps<{
    variant?: 'primary' | 'secondary'
  }>(),
  {
    variant: 'secondary',
  },
)
</script>

<template>
  <button type="button" class="app-button" :class="`app-button--${variant}`">
    <slot />
  </button>
</template>

<style scoped>
.app-button {
  padding: 0.5rem 1.25rem;
  border: 1px solid transparent;
  border-radius: 8px;
  font: inherit;
  cursor: pointer;
  transition: background 0.15s ease;
}

.app-button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.app-button--primary {
  background: #333;
  color: #fff;
  border-color: #333;
}

.app-button--primary:hover:not(:disabled) {
  background: #555;
}

.app-button--secondary {
  background: #fff;
  color: inherit;
  border-color: #ccc;
}

.app-button--secondary:hover:not(:disabled) {
  background: #f4f4f4;
}
</style>

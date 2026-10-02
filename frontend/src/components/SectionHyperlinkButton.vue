<script setup lang="ts">
defineProps<{
  label: string
  variant: string
  to?: string
}>()

const emit = defineEmits<{
  click: []
}>()
</script>

<template>
  <RouterLink v-if="to" :to="to" class="tile" :class="`tile--${variant}`">
    {{ label }}
  </RouterLink>

  <button v-else type="button" class="tile" :class="`tile--${variant}`" @click="emit('click')">
    {{ label }}
  </button>
</template>

<style scoped>
.tile {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  aspect-ratio: 1;
  padding: 1rem;
  border: none;
  border-radius: 12px;
  font: inherit;
  font-weight: 500;
  font-size: 1.1rem;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  transition:
    transform 0.15s ease,
    box-shadow 0.15s ease;
}

.tile:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.tile:focus-visible {
  outline: 2px solid #534ab7;
  outline-offset: 3px;
}

.tile--enter {
  background: #e1f5ee;
  color: #085041;
}
.tile--entries {
  background: #eeedfe;
  color: #3c3489;
}
.tile--tasks {
  background: #faeeda;
  color: #633806;
}
.tile--contacts {
  background: #fbeaf0;
  color: #72243e;
}
.tile--upskill {
  background: #e6f1fb;
  color: #0c447c;
}
.tile--tips {
  background: #faece7;
  color: #712b13;
}
</style>

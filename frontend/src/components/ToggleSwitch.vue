<script setup lang="ts">
const checked = defineModel<boolean>({ required: true })
</script>

<template>
  <label class="toggle">
    <input v-model="checked" type="checkbox" role="switch" class="toggle__input" />
    <span class="toggle__track" aria-hidden="true">
      <span class="toggle__thumb"></span>
    </span>
    <span class="toggle__label"><slot /></span>
  </label>
</template>

<style scoped>
.toggle {
  position: relative;
  display: inline-flex;
  align-items: center;
  gap: 0.625rem;
  cursor: pointer;
}

.toggle__input {
  position: absolute;
  width: 1px;
  height: 1px;
  opacity: 0;
}

.toggle__track {
  position: relative;
  flex-shrink: 0;
  width: 2.5rem;
  height: 1.375rem;
  border-radius: 999px;
  background: #ccc;
  transition: background 0.2s ease;
}

.toggle__thumb {
  position: absolute;
  top: 0.1875rem;
  left: 0.1875rem;
  width: 1rem;
  height: 1rem;
  border-radius: 50%;
  background: #fff;
  transition: transform 0.2s ease;
}

.toggle__input:checked + .toggle__track {
  background: #333;
}

.toggle__input:checked + .toggle__track .toggle__thumb {
  transform: translateX(1.125rem);
}

.toggle__input:focus-visible + .toggle__track {
  outline: 2px solid #534ab7;
  outline-offset: 2px;
}
</style>

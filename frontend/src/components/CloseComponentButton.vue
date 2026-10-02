<template>
  <button type="button" class="close-button">
    <slot>Close</slot>
  </button>
</template>

<style scoped>
.close-button {
  padding: 0.5rem 1.25rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #fff;
  color: inherit;
  font: inherit;
  cursor: pointer;
}

.close-button:hover {
  background: #f4f4f4;
}
</style>

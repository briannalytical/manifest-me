<template>
  <button type="button" class="save-button">
    <slot>Save</slot>
  </button>
</template>

<style scoped>
.save-button {
  padding: 0.5rem 1.25rem;
  border: 1px solid #333;
  border-radius: 8px;
  background: #333;
  color: #fff;
  font: inherit;
  cursor: pointer;
}

.save-button:hover {
  background: #555;
}

.save-button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>

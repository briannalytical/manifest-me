<script setup lang="ts">
defineProps<{
  isLoggedIn: boolean
}>()

const emit = defineEmits<{
  login: []
  logout: []
}>()
</script>

<template>
  <header class="title-header">
    <RouterLink :to="isLoggedIn ? '/home' : '/'" class="title"> Manifest-Me </RouterLink>

    <nav class="nav">
      <RouterLink to="/about" class="nav-item">About</RouterLink>
      <RouterLink v-if="isLoggedIn" to="/profile" class="nav-item">Profile</RouterLink>
      <button v-if="isLoggedIn" class="nav-item" @click="emit('logout')">Log out</button>
      <button v-else class="nav-item nav-item--primary" @click="emit('login')">Log in</button>
    </nav>
  </header>
</template>

<style scoped>
.title-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  border-bottom: 1px solid #ddd;
}

.title {
  font-size: 1.5rem;
  font-weight: 500;
  color: inherit;
  text-decoration: none;
}

.nav {
  display: flex;
  gap: 0.5rem;
}

.nav-item {
  padding: 0.5rem 1rem;
  border: 1px solid transparent;
  border-radius: 8px;
  background: none;
  color: inherit;
  font: inherit;
  text-decoration: none;
  cursor: pointer;
}

.nav-item:hover {
  background: #f4f4f4;
}

.nav-item.router-link-active {
  border-color: #ddd;
}

.nav-item--primary {
  background: #333;
  color: #fff;
}

.nav-item--primary:hover {
  background: #555;
}
</style>

<script setup lang="ts">
import TitleHeader from '@/components/TitleHeader.vue'
import TextContent from '@/components/TextContent.vue'
import HeaderText from '@/components/HeaderText.vue'
</script>

<template>
  <TitleHeader :is-logged-in="true" />

  <main class="about">
    <div class="container">
      <div class="header-container">
        <HeaderText />
      </div>
      <div class="content-container">
        <TextContent />
      </div>
    </div>
  </main>
</template>

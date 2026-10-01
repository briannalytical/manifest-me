<script setup lang="ts">
import TitleHeader from '@/components/TitleHeader.vue'
import TextContent from '@/components/TextContent.vue'
import AnalyticsDisplay from '@/components/AnalyticsDisplay.vue'
import SectionHyperlinkButton from '@/components/SectionHyperlinkButton.vue'

const sections = [
  { label: 'Enter', to: '/entries/new', variant: 'enter' },
  { label: 'View / edit', to: '/entries', variant: 'entries' },
  { label: 'Tasks', to: '/tasks', variant: 'tasks' },
  { label: 'Contact book', to: '/contacts', variant: 'contacts' },
  { label: 'Upskill', to: '/resources', variant: 'upskill' },
  { label: 'Job search tips', to: '/tips', variant: 'tips' },
]
</script>

<template>
  <TitleHeader :is-logged-in="true" />

  <main class="home">
    <AnalyticsDisplay />

    <div class="section-grid">
      <SectionHyperlinkButton
        v-for="section in sections"
        :key="section.to"
        :label="section.label"
        :to="section.to"
        :variant="section.variant"
      />
    </div>
  </main>
</template>

<style scoped>
.home {
  max-width: 960px;
  margin: 0 auto;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3rem;
}

.section-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
  width: 100%;
}
</style>

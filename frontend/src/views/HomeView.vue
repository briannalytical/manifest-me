<script setup lang="ts">
import { ref } from 'vue'
import TitleHeader from '@/components/TitleHeader.vue'
import AnalyticsDisplay from '@/components/AnalyticsDisplay.vue'
import SectionHyperlinkButton from '@/components/SectionHyperlinkButton.vue'
import ApplicationEntry from '@/components/application/ApplicationEntry.vue'

const showEntry = ref(false)
</script>

<template>
  <main class="home">
    <TitleHeader :is-logged-in="true" />
    <div class="content">
      <div class="analytics-wrapper"><AnalyticsDisplay /></div>
      <div class="section-grid">
        <SectionHyperlinkButton
          class=""
          label="Enter"
          variant="enter"
          @click="showEntry = true"
        />
        <SectionHyperlinkButton
          class=""
          label="View / edit"
          variant="entries"
          to="/entries"
        />
        <SectionHyperlinkButton
          class=""
          label="Tasks"
          variant="tasks"
          to="/tasks"
        />
        <SectionHyperlinkButton
          class=""
          label="Contact book"
          variant="contacts"
          to="/contacts"
        />
        <SectionHyperlinkButton
          class=""
          label="Upskill"
          variant="upskill"
          to="/resources"
        />
        <SectionHyperlinkButton
          class=""
          label="Job search tips"
          variant="tips"
          to="/tips"
        />
      </div>
    </div>
  </main>

  <ApplicationEntry
    v-if="showEntry"
    @close="showEntry = false"
    @saved="showEntry = false"
  />
</template>

<style scoped>
.home {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.content {
  box-sizing: border-box;
  width: 100%;
  max-width: 960px;
  margin: 0 auto;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 3rem;
}

.analytics-wrapper {
  width: 100%;
}

.section-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 2rem;
  width: 100%;
}
</style>

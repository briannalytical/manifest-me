<script setup lang="ts">
import type { Contact } from '@/types/contact'

defineProps<{
  contact: Contact
}>()
</script>

<template>
  <article class="contact-card">
    <header class="contact-card__header">
      <div>
        <h3 class="contact-card__name">
          {{ contact.name }}
          <span v-if="contact.recruiter" class="badge">Recruiter</span>
        </h3>
        <p v-if="contact.role || contact.companyName" class="contact-card__meta">
          {{ [contact.role, contact.companyName].filter(Boolean).join(' · ') }}
        </p>
      </div>
      <div class="contact-card__actions">
        <slot name="actions" />
      </div>
    </header>

    <ul v-if="contact.email || contact.phone || contact.linkedInUrl" class="contact-card__details">
      <li v-if="contact.email">
        <a :href="`mailto:${contact.email}`">{{ contact.email }}</a>
      </li>
      <li v-if="contact.phone">
        <a :href="`tel:${contact.phone}`">{{ contact.phone }}</a>
      </li>
      <li v-if="contact.linkedInUrl">
        <a :href="contact.linkedInUrl" target="_blank" rel="noopener">LinkedIn</a>
      </li>
    </ul>

    <p v-if="contact.notes" class="contact-card__notes">{{ contact.notes }}</p>
  </article>
</template>

<style scoped>
.contact-card {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
  padding: 1.25rem;
  border: 1px solid #ddd;
  border-radius: 12px;
  background: #fff;
}

.contact-card__header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  gap: 1rem;
}

.contact-card__name {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin: 0;
  font-size: 1.1rem;
  font-weight: 500;
}

.badge {
  padding: 0.125rem 0.5rem;
  border-radius: 999px;
  background: #eeedfe;
  color: #3c3489;
  font-size: 0.75rem;
  font-weight: 500;
}

.contact-card__meta {
  margin-top: 0.25rem;
  color: #666;
  font-size: 0.9rem;
}

.contact-card__actions {
  display: flex;
  gap: 0.5rem;
}

.contact-card__details {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  list-style: none;
  padding: 0;
  margin: 0;
  font-size: 0.9rem;
}

.contact-card__notes {
  color: #444;
  font-size: 0.9rem;
}
</style>

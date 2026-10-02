<script setup lang="ts">
import { computed, ref } from 'vue'
import ContactCard from '@/components/contacts/ContactCard.vue'
import ContactForm from '@/components/contacts/ContactForm.vue'
import AppButton from '@/components/AppButton.vue'
import { useContactsStore } from '@/stores/contacts'
import type { Contact, ContactInput } from '@/types/contact'

const props = defineProps<{
  entryId?: number
}>()

const store = useContactsStore()

const showForm = ref(false)
const selectedContactId = ref<number | null>(null)

const linkedContacts = computed(() =>
  props.entryId === undefined ? [] : store.contactsForEntry(props.entryId),
)

const availableContacts = computed(() =>
  store.contacts.filter((c) => !linkedContacts.value.some((linked) => linked.id === c.id)),
)

function optionLabel(contact: Contact) {
  return contact.companyName ? `${contact.name} — ${contact.companyName}` : contact.name
}

function linkSelected() {
  if (props.entryId === undefined || selectedContactId.value === null) return
  store.linkContact(props.entryId, selectedContactId.value)
  selectedContactId.value = null
}

function createAndLink(input: ContactInput) {
  if (props.entryId === undefined) return
  const contact = store.addContact(input)
  store.linkContact(props.entryId, contact.id)
  showForm.value = false
}

function unlink(contactId: number) {
  if (props.entryId === undefined) return
  store.unlinkContact(props.entryId, contactId)
}
</script>

<template>
  <div class="contact-tab">
    <p v-if="entryId === undefined" class="empty">Save the basic info first to link contacts.</p>

    <template v-else>
      <div class="link-row">
        <select v-model="selectedContactId">
          <option :value="null" disabled>Link an existing contact…</option>
          <option v-for="contact in availableContacts" :key="contact.id" :value="contact.id">
            {{ optionLabel(contact) }}
          </option>
        </select>
        <AppButton variant="primary" :disabled="selectedContactId === null" @click="linkSelected">
          Link
        </AppButton>
        <AppButton v-if="!showForm" @click="showForm = true">New contact</AppButton>
      </div>

      <ContactForm v-if="showForm" @submit="createAndLink" @cancel="showForm = false" />

      <p v-if="!linkedContacts.length && !showForm" class="empty">
        No contacts linked to this application yet.
      </p>

      <ContactCard v-for="contact in linkedContacts" :key="contact.id" :contact="contact">
        <template #actions>
          <AppButton @click="unlink(contact.id)">Unlink</AppButton>
        </template>
      </ContactCard>
    </template>
  </div>
</template>

<style scoped>
.contact-tab {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.link-row {
  display: flex;
  gap: 0.5rem;
}

.link-row select {
  flex: 1;
  padding: 0.5rem 0.75rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  font: inherit;
}

.empty {
  color: #666;
}
</style>

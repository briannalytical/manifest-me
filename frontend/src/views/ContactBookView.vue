<script setup lang="ts">
import { ref } from 'vue'
import TitleHeader from '@/components/TitleHeader.vue'
import ContactCard from '@/components/contacts/ContactCard.vue'
import ContactForm from '@/components/contacts/ContactForm.vue'
import SaveDataButton from '@/components/SaveDataButton.vue'
import CloseComponentButton from '@/components/CloseComponentButton.vue'
import { useContactsStore } from '@/stores/contacts'
import type { Contact, ContactInput } from '@/types/contact'

const store = useContactsStore()

const isAdding = ref(false)
const editingId = ref<number | null>(null)

function startAdd() {
  editingId.value = null
  isAdding.value = true
}

function handleAdd(input: ContactInput) {
  store.addContact(input)
  isAdding.value = false
}

function handleUpdate(contact: Contact, input: ContactInput) {
  store.updateContact(contact.id, input)
  editingId.value = null
}

function handleDelete(contact: Contact) {
  if (confirm(`Delete ${contact.name}? This also unlinks them from any applications.`)) {
    store.removeContact(contact.id)
  }
}
</script>

<template>
  <TitleHeader :is-logged-in="true" />

  <main class="contacts">
    <div class="contacts__heading">
      <h1>Contact book</h1>
      <SaveDataButton v-if="!isAdding" @click="startAdd">Add contact</SaveDataButton>
    </div>

    <ContactForm v-if="isAdding" @submit="handleAdd" @cancel="isAdding = false" />

    <p v-if="!store.contacts.length && !isAdding" class="empty">No contacts yet.</p>

    <template v-for="contact in store.contacts" :key="contact.id">
      <ContactForm
        v-if="editingId === contact.id"
        :initial="contact"
        @submit="(input) => handleUpdate(contact, input)"
        @cancel="editingId = null"
      />
      <ContactCard v-else :contact="contact">
        <template #actions>
          <CloseComponentButton @click="editingId = contact.id">Edit</CloseComponentButton>
          <CloseComponentButton @click="handleDelete(contact)">Delete</CloseComponentButton>
        </template>
      </ContactCard>
    </template>
  </main>
</template>

<style scoped>
.contacts {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.contacts__heading {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.empty {
  color: #666;
}
</style>

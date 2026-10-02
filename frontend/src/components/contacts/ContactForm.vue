<script setup lang="ts">
import { computed, ref } from 'vue'
import type { Contact, ContactInput } from '@/types/contact'
import SaveDataButton from '@/components/SaveDataButton.vue'
import CloseComponentButton from '@/components/CloseComponentButton.vue'

const props = defineProps<{
  initial?: Contact
}>()

const emit = defineEmits<{
  submit: [value: ContactInput]
  cancel: []
}>()

const draft = ref<ContactInput>({
  name: props.initial?.name ?? '',
  role: props.initial?.role ?? null,
  companyName: props.initial?.companyName ?? null,
  email: props.initial?.email ?? null,
  phone: props.initial?.phone ?? null,
  linkedinUrl: props.initial?.linkedInUrl ?? null,
  recruiter: props.initial?.recruiter ?? false,
  notes: props.initial?.notes ?? null,
})

const canSave = computed(() => draft.value.name.trim().length > 0)

function submit() {
  if (canSave.value) emit('submit', { ...draft.value })
}
</script>

<template>
  <div class="contact-form">
    <div class="fields">
      <div class="field">
        <label for="contactName">Name</label>
        <input id="contactName" v-model="draft.name" type="text" required />
      </div>

      <div class="field">
        <label for="contactRole">Role</label>
        <input id="contactRole" v-model="draft.role" type="text" />
      </div>

      <div class="field">
        <label for="contactCompany">Company</label>
        <input id="contactCompany" v-model="draft.companyName" type="text" />
      </div>

      <div class="field">
        <label for="contactEmail">Email</label>
        <input id="contactEmail" v-model="draft.email" type="email" />
      </div>

      <div class="field">
        <label for="contactPhone">Phone</label>
        <input id="contactPhone" v-model="draft.phone" type="tel" />
      </div>

      <div class="field">
        <label for="contactLinkedin">LinkedIn URL</label>
        <input id="contactLinkedin" v-model="draft.linkedInUrl" type="url" />
      </div>

      <label class="field field--full checkbox">
        <input v-model="draft.recruiter" type="checkbox" />
        This person is a recruiter
      </label>

      <div class="field field--full">
        <label for="contactNotes">Notes</label>
        <textarea id="contactNotes" v-model="draft.notes"></textarea>
      </div>
    </div>

    <div class="actions">
      <CloseComponentButton @click="emit('cancel')">Cancel</CloseComponentButton>
      <SaveDataButton :disabled="!canSave" @click="submit" />
    </div>
  </div>
</template>

<style scoped>
.contact-form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
  padding: 1.25rem;
  border: 1px solid #ddd;
  border-radius: 12px;
  background: #fafafa;
}

.fields {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 0.375rem;
}

.field--full {
  grid-column: 1 / -1;
}

label {
  font-weight: 500;
  font-size: 0.9rem;
}

input[type='text'],
input[type='email'],
input[type='tel'],
input[type='url'],
textarea {
  padding: 0.5rem 0.75rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #fff;
  font: inherit;
}

textarea {
  min-height: 5rem;
  resize: vertical;
}

.checkbox {
  flex-direction: row;
  align-items: center;
  gap: 0.5rem;
  font-weight: 400;
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.75rem;
}
</style>

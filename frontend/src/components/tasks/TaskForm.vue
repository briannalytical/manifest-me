<script setup lang="ts">
import { computed, ref } from 'vue'
import { TASK_KINDS, type Task, type TaskInput } from '@/types/task'
import { formatLabel } from '@/utils/formatLabel'
import AppButton from '@/components/AppButton.vue'

const props = defineProps<{
  initial?: Task
  entryId?: number | null
}>()

const emit = defineEmits<{
  submit: [value: TaskInput]
  cancel: []
}>()

const draft = ref<TaskInput>({
  entryId: props.initial?.entryId ?? props.entryId ?? null,
  title: props.initial?.title ?? '',
  notes: props.initial?.notes ?? null,
  kind: props.initial?.kind ?? 'OTHER',
  dueDate: props.initial?.dueDate ?? null,
})

const canSave = computed(() => draft.value.title.trim().length > 0)

function submit() {
  if (canSave.value) emit('submit', { ...draft.value })
}
</script>

<template>
  <div class="task-form">
    <div class="fields">
      <div class="field field--full">
        <label for="taskTitle">Task</label>
        <input id="taskTitle" v-model="draft.title" type="text" required />
      </div>

      <div class="field">
        <label for="taskKind">Type</label>
        <select id="taskKind" v-model="draft.kind">
          <option v-for="option in TASK_KINDS" :key="option" :value="option">
            {{ formatLabel(option) }}
          </option>
        </select>
      </div>

      <div class="field">
        <label for="taskDue">Due date</label>
        <input id="taskDue" v-model="draft.dueDate" type="date" />
      </div>

      <div class="field field--full">
        <label for="taskNotes">Notes</label>
        <textarea id="taskNotes" v-model="draft.notes"></textarea>
      </div>
    </div>

    <div class="actions">
      <AppButton @click="emit('cancel')">Cancel</AppButton>
      <AppButton variant="primary" :disabled="!canSave" @click="submit">Save</AppButton>
    </div>
  </div>
</template>

<style scoped>
.task-form {
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
input[type='date'],
select,
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

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.75rem;
}
</style>

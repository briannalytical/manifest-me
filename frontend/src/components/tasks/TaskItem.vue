<script setup lang="ts">
import { computed } from 'vue'
import type { Task } from '@/types/task'
import { formatDate, formatLabel } from '@/utils/formatLabel.ts'

const props = defineProps<{
  task: Task
}>()

const emit = defineEmits<{
  toggle: []
}>()

const today = new Date().toLocaleDateString('en-CA')

const isDone = computed(() => props.task.completedAt !== null)

const isOverdue = computed(
  () => !isDone.value && props.task.dueDate !== null && props.task.dueDate < today,
)
</script>

<template>
  <article class="task-item" :class="{ 'task-item--done': isDone, 'task-item--overdue': isOverdue }">
    <input
      type="checkbox"
      class="task-item__check"
      :checked="isDone"
      :aria-label="`Mark ${task.title} complete`"
      @change="emit('toggle')"
    />

    <div class="task-item__body">
      <p class="task-item__title">{{ task.title }}</p>
      <p class="task-item__meta">
        <span class="kind">{{ formatLabel(task.kind) }}</span>
        <span v-if="task.dueDate">Due {{ formatDate(task.dueDate) }}</span>
        <span v-if="isOverdue" class="overdue">Overdue</span>
      </p>
      <p v-if="task.notes" class="task-item__notes">{{ task.notes }}</p>
    </div>

    <div class="task-item__actions">
      <slot name="actions" />
    </div>
  </article>
</template>

<style scoped>
.task-item {
  display: flex;
  align-items: flex-start;
  gap: 0.75rem;
  padding: 1rem;
  border: 1px solid #ddd;
  border-radius: 12px;
  background: #fff;
}

.task-item--overdue {
  border-color: #e8a598;
}

.task-item__check {
  width: 1.125rem;
  height: 1.125rem;
  margin-top: 0.125rem;
  cursor: pointer;
}

.task-item__body {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
}

.task-item__title {
  font-weight: 500;
}

.task-item--done .task-item__title {
  text-decoration: line-through;
  color: #888;
}

.task-item__meta {
  display: flex;
  flex-wrap: wrap;
  gap: 0.75rem;
  color: #666;
  font-size: 0.85rem;
}

.kind {
  padding: 0 0.5rem;
  border-radius: 999px;
  background: #f4f4f4;
}

.overdue {
  color: #993c1d;
  font-weight: 500;
}

.task-item__notes {
  color: #444;
  font-size: 0.9rem;
}

.task-item__actions {
  display: flex;
  gap: 0.5rem;
}
</style>

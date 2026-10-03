<script setup lang="ts">
import { computed, ref } from 'vue'
import TaskItem from '@/components/tasks/TaskItem.vue'
import TaskForm from '@/components/tasks/TaskForm.vue'
import AppButton from '@/components/AppButton.vue'
import { useTasksStore } from '@/stores/tasks'
import type { Task, TaskInput } from '@/types/task'

const props = defineProps<{
  entryId?: number
}>()

const store = useTasksStore()

const isAdding = ref(false)
const editingId = ref<number | null>(null)

const entryTasks = computed(() =>
  props.entryId === undefined ? [] : store.tasksForEntry(props.entryId),
)

const openCount = computed(() => entryTasks.value.filter((t) => t.completedAt === null).length)

function handleAdd(input: TaskInput) {
  store.addTask(input)
  isAdding.value = false
}

function handleUpdate(task: Task, input: TaskInput) {
  store.updateTask(task.id, input)
  editingId.value = null
}

function handleDelete(task: Task) {
  if (confirm(`Delete "${task.title}"?`)) store.removeTask(task.id)
}
</script>

<template>
  <div class="tasks-tab">
    <p v-if="entryId === undefined" class="empty">Save the basic info first to add tasks.</p>

    <template v-else>
      <div class="tasks-tab__heading">
        <p class="count">{{ openCount }} open</p>
        <AppButton v-if="!isAdding" variant="primary" @click="isAdding = true">Add task</AppButton>
      </div>

      <TaskForm
        v-if="isAdding"
        :entry-id="entryId"
        @submit="handleAdd"
        @cancel="isAdding = false"
      />

      <p v-if="!entryTasks.length && !isAdding" class="empty">
        No tasks for this application yet.
      </p>

      <template v-for="task in entryTasks" :key="task.id">
        <TaskForm
          v-if="editingId === task.id"
          :initial="task"
          @submit="(input) => handleUpdate(task, input)"
          @cancel="editingId = null"
        />
        <TaskItem v-else :task="task" @toggle="store.toggleComplete(task.id)">
          <template #actions>
            <AppButton @click="editingId = task.id">Edit</AppButton>
            <AppButton @click="handleDelete(task)">Delete</AppButton>
          </template>
        </TaskItem>
      </template>
    </template>
  </div>
</template>

<style scoped>
.tasks-tab {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

.tasks-tab__heading {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.count {
  color: #666;
}

.empty {
  color: #666;
}
</style>

import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { Task, TaskInput } from '@/types/task'

export const useTasksStore = defineStore('tasks', () => {
  const tasks = ref<Task[]>([
    {
      id: 1,
      entryId: 1,
      title: 'Send thank-you note to Jordan',
      notes: null,
      kind: 'THANK_YOU',
      dueDate: '2026-10-01',
      completedAt: null,
      createdAt: '2026-09-28T12:00:00Z',
    },
    {
      id: 2,
      entryId: 1,
      title: 'Prep for technical screen',
      notes: 'Review SQL window functions.',
      kind: 'PREP',
      dueDate: '2026-10-08',
      completedAt: null,
      createdAt: '2026-09-29T12:00:00Z',
    },
    {
      id: 3,
      entryId: null,
      title: 'Update resume summary',
      notes: null,
      kind: 'OTHER',
      dueDate: null,
      completedAt: null,
      createdAt: '2026-09-30T12:00:00Z',
    },
  ])

  let nextId = 4

  function openFirstThenByDueDate(a: Task, b: Task) {
    if ((a.completedAt === null) !== (b.completedAt === null)) {
      return a.completedAt === null ? -1 : 1
    }
    if (a.dueDate === b.dueDate) return 0
    if (a.dueDate === null) return 1
    if (b.dueDate === null) return -1
    return a.dueDate < b.dueDate ? -1 : 1
  }

  function tasksForEntry(entryId: number) {
    return tasks.value.filter((t) => t.entryId === entryId).sort(openFirstThenByDueDate)
  }

  function addTask(input: TaskInput): Task {
    const task: Task = {
      ...input,
      id: nextId++,
      completedAt: null,
      createdAt: new Date().toISOString(),
    }
    tasks.value.push(task)
    return task
  }

  function updateTask(id: number, input: TaskInput) {
    const existing = tasks.value.find((t) => t.id === id)
    if (existing) Object.assign(existing, input)
  }

  function toggleComplete(id: number) {
    const task = tasks.value.find((t) => t.id === id)
    if (task) task.completedAt = task.completedAt ? null : new Date().toISOString()
  }

  function removeTask(id: number) {
    tasks.value = tasks.value.filter((t) => t.id !== id)
  }

  return { tasks, tasksForEntry, addTask, updateTask, toggleComplete, removeTask }
})

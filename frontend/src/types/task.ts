export type TaskKind = 'FOLLOW_UP' | 'PREP' | 'APPLY' | 'RESEARCH' | 'THANK_YOU' | 'OTHER'

export interface Task {
  id: number
  entryId: number | null
  title: string
  notes: string | null
  kind: TaskKind
  dueDate: string | null
  completedAt: string | null
  createdAt: string
}

export type TaskInput = Omit<Task, 'id' | 'completedAt' | 'createdAt'>

export const TASK_KINDS: TaskKind[] = ['FOLLOW_UP', 'PREP', 'APPLY', 'RESEARCH', 'THANK_YOU', 'OTHER']

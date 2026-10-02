export type Source = 'SELF' | 'RECRUITER'

export type Status =
  | 'APPLIED'
  | 'SCREENING'
  | 'INTERVIEWING'
  | 'OFFER'
  | 'REJECTED'
  | 'GHOSTED'
  | 'WITHDRAWN'

export type WorkArrangement = 'REMOTE' | 'HYBRID' | 'ONSITE'

export interface Entry {
  id: number
  positionTitle: string
  companyName: string
  sourceType: Source
  status: Status
  dateApplied: string
  jobUrl: string | null
  workArrangements: WorkArrangement[]
  locations: string[]
  salaryRange: string | null
  notes: string | null
  createdAt: string
  updatedAt: string
}

export type EntryInput = Omit<Entry, 'id' | 'createdAt' | 'updatedAt'>

export const SOURCES: Source[] = ['SELF', 'RECRUITER']

export const STATUSES: Status[] = [
  'APPLIED',
  'SCREENING',
  'INTERVIEWING',
  'OFFER',
  'REJECTED',
  'GHOSTED',
  'WITHDRAWN',
]

export const WORK_ARRANGEMENTS: WorkArrangement[] = ['REMOTE', 'HYBRID', 'ONSITE']

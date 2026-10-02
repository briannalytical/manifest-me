export interface Contact {
  id: number
  name: string
  role: string | null
  companyName: string
  email: string | null
  phone: string | null
  linkedInUrl: string | null
  recruiter: boolean
  notes: string | null
  createdAt: string
}

export type ContactInput = Omit<Contact, 'id' | 'createdAt'>

import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { Contact, ContactInput } from '@/types/contact'

export const useContactsStore = defineStore('contacts', () => {
  const contacts = ref<Contact[]>([
    {
      id: 1,
      name: 'Jordan Lee',
      role: 'Technical Recruiter',
      companyName: 'Acme Corp',
      email: 'jordan.lee@example.com',
      phone: null,
      linkedInUrl: 'https://www.linkedin.com/in/example',
      recruiter: true,
      notes: 'Reached out about the data engineer role.',
      createdAt: '2026-09-15T14:00:00Z',
    },
    {
      id: 2,
      name: 'Sam Patel',
      role: 'Engineering Manager',
      companyName: 'Northwind',
      email: null,
      phone: '555-0142',
      linkedInUrl: null,
      recruiter: false,
      notes: null,
      createdAt: '2026-09-20T09:30:00Z',
    },
  ])

  const links = ref<{ entryId: number; contactId: number }[]>([{ entryId: 1, contactId: 1 }])

  let nextId = 3

  function addContact(input: ContactInput): Contact {
    const contact: Contact = { ...input, id: nextId++, createdAt: new Date().toISOString() }
    contacts.value.push(contact)
    return contact
  }

  function updateContact(id: number, input: ContactInput) {
    const existing = contacts.value.find((c) => c.id === id)
    if (existing) Object.assign(existing, input)
  }

  function removeContact(id: number) {
    contacts.value = contacts.value.filter((c) => c.id !== id)
    links.value = links.value.filter((l) => l.contactId !== id)
  }

  function contactsForEntry(entryId: number) {
    const ids = links.value.filter((l) => l.entryId === entryId).map((l) => l.contactId)
    return contacts.value.filter((c) => ids.includes(c.id))
  }

  function linkContact(entryId: number, contactId: number) {
    const exists = links.value.some((l) => l.entryId === entryId && l.contactId === contactId)
    if (!exists) links.value.push({ entryId, contactId })
  }

  function unlinkContact(entryId: number, contactId: number) {
    links.value = links.value.filter((l) => !(l.entryId === entryId && l.contactId === contactId))
  }

  return {
    contacts,
    addContact,
    updateContact,
    removeContact,
    contactsForEntry,
    linkContact,
    unlinkContact,
  }
})

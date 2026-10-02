<script setup lang="ts">
import { computed, ref } from 'vue'
import { SOURCES, STATUSES, WORK_ARRANGEMENTS, type EntryInput } from '@/types/entry'

const props = defineProps<{
  modelValue: EntryInput
}>()

const emit = defineEmits<{
  'update:modelValue': [value: EntryInput]
}>()

function field<K extends keyof EntryInput>(key: K) {
  return computed({
    get: () => props.modelValue[key],
    set: (value: EntryInput[K]) => {
      emit('update:modelValue', { ...props.modelValue, [key]: value })
    },
  })
}

const companyName = field('companyName')
const positionTitle = field('positionTitle')
const status = field('status')
const sourceType = field('sourceType')
const dateApplied = field('dateApplied')
const salaryRange = field('salaryRange')
const jobUrl = field('jobUrl')
const workArrangements = field('workArrangements')
const locations = field('locations')
const notes = field('notes')

const newLocation = ref('')

function addLocation() {
  const value = newLocation.value.trim()
  if (value && !locations.value.includes(value)) {
    locations.value = [...locations.value, value]
  }
  newLocation.value = ''
}

function removeLocation(location: string) {
  locations.value = locations.value.filter((l) => l !== location)
}

function formatLabel(value: string) {
  return value.charAt(0) + value.slice(1).toLowerCase()
}
</script>

<template>
  <div class="basic-info">
    <div class="field">
      <label for="companyName">Company</label>
      <input id="companyName" v-model="companyName" type="text" required />
    </div>

    <div class="field">
      <label for="positionTitle">Position</label>
      <input id="positionTitle" v-model="positionTitle" type="text" required />
    </div>

    <div class="field">
      <label for="status">Status</label>
      <select id="status" v-model="status">
        <option v-for="option in STATUSES" :key="option" :value="option">
          {{ formatLabel(option) }}
        </option>
      </select>
    </div>

    <div class="field">
      <label for="sourceType">Source</label>
      <select id="sourceType" v-model="sourceType">
        <option v-for="option in SOURCES" :key="option" :value="option">
          {{ formatLabel(option) }}
        </option>
      </select>
    </div>

    <div class="field">
      <label for="dateApplied">Date applied</label>
      <input id="dateApplied" v-model="dateApplied" type="date" required />
    </div>

    <div class="field">
      <label for="salaryRange">Salary range</label>
      <input id="salaryRange" v-model="salaryRange" type="text" />
    </div>

    <div class="field field--full">
      <label for="jobUrl">Job posting URL</label>
      <input id="jobUrl" v-model="jobUrl" type="url" />
    </div>

    <fieldset class="field field--full">
      <legend>Work arrangement</legend>
      <div class="checkbox-row">
        <label v-for="option in WORK_ARRANGEMENTS" :key="option" class="checkbox">
          <input v-model="workArrangements" type="checkbox" :value="option" />
          {{ formatLabel(option) }}
        </label>
      </div>
    </fieldset>

    <div class="field field--full">
      <label for="newLocation">Locations</label>
      <div class="location-input">
        <input
          id="newLocation"
          v-model="newLocation"
          type="text"
          placeholder="e.g. Chicago, IL"
          @keydown.enter.prevent="addLocation"
        />
        <button type="button" @click="addLocation">Add</button>
      </div>
      <ul v-if="locations.length" class="location-list">
        <li v-for="location in locations" :key="location" class="location-chip">
          {{ location }}
          <button type="button" aria-label="Remove location" @click="removeLocation(location)">×</button>
        </li>
      </ul>
    </div>

    <div class="field field--full">
      <label for="notes">Notes</label>
      <textarea id="notes" v-model="notes"></textarea>
    </div>
  </div>
</template>

<style scoped>
.basic-info {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1.25rem;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 0.375rem;
}

.field--full {
  grid-column: 1 / -1;
}

fieldset.field {
  border: none;
  padding: 0;
  margin: 0;
}

label,
legend {
  font-weight: 500;
  font-size: 0.9rem;
}

input[type='text'],
input[type='url'],
input[type='date'],
select,
textarea {
  padding: 0.5rem 0.75rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  font: inherit;
}

textarea {
  min-height: 6rem;
  resize: vertical;
}

.checkbox-row {
  display: flex;
  gap: 1.5rem;
}

.checkbox {
  display: flex;
  align-items: center;
  gap: 0.375rem;
  font-weight: 400;
}

.location-input {
  display: flex;
  gap: 0.5rem;
}

.location-input input {
  flex: 1;
}

.location-input button {
  padding: 0.5rem 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #fff;
  font: inherit;
  cursor: pointer;
}

.location-list {
  display: flex;
  flex-wrap: wrap;
  gap: 0.5rem;
  list-style: none;
  padding: 0;
  margin: 0;
}

.location-chip {
  display: flex;
  align-items: center;
  gap: 0.25rem;
  padding: 0.25rem 0.75rem;
  background: #f4f4f4;
  border-radius: 999px;
  font-size: 0.9rem;
}

.location-chip button {
  border: none;
  background: none;
  font-size: 1rem;
  line-height: 1;
  cursor: pointer;
}
</style>

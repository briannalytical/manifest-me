<script setup lang="ts">
import { computed, ref } from 'vue'
import BasicInfoTab from '@/components/application/ApplicationBasicInfoTab.vue'
import ContactsTab from '@/components/application/ApplicationContactTab.vue'
import LineageTab from '@/components/application/ApplicationLineageTab.vue'
import TasksTab from '@/components/application/ApplicationTasksTab.vue'

type TabKey = 'basic' | 'contacts' | 'lineage' | 'tasks'

const props = defineProps<{
  entryId?: number
}>()

const emit = defineEmits<{
  close: []
  saved: []
}>()

const tabs: { key: TabKey; label: string }[] = [
  { key: 'basic', label: 'Basic info' },
  { key: 'contacts', label: 'Contacts' },
  { key: 'lineage', label: 'Lineage' },
  { key: 'tasks', label: 'Tasks' },
]

const activeTab = ref<TabKey>('basic')

const isEditing = computed(() => props.entryId !== undefined)
</script>

<template>
  <Teleport to="body">
    <div class="backdrop" @click.self="emit('close')">
      <div class="entry-card" role="dialog" aria-modal="true">
        <nav class="tab-strip">
          <button
            v-for="tab in tabs"
            :key="tab.key"
            class="tab"
            :class="{ 'tab--active': activeTab === tab.key }"
            :disabled="tab.key !== 'basic' && !isEditing"
            @click="activeTab = tab.key"
          >
            {{ tab.label }}
          </button>
        </nav>

        <section class="tab-content">
          <BasicInfoTab v-if="activeTab === 'basic'" :entry-id="entryId" />
          <ContactsTab v-else-if="activeTab === 'contacts'" :entry-id="entryId" />
          <LineageTab v-else-if="activeTab === 'lineage'" :entry-id="entryId" />
          <TasksTab v-else-if="activeTab === 'tasks'" :entry-id="entryId" />
        </section>

        <footer class="actions">
          <button class="btn" @click="emit('close')">Cancel</button>
          <button class="btn btn--primary" @click="emit('saved')">Save</button>
        </footer>
      </div>
    </div>
  </Teleport>
</template>

<style scoped>
.backdrop {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 100;
}

.entry-card {
  width: 75vw;
  height: 75vh;
  background: #fff;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.tab-strip {
  display: flex;
  gap: 0.25rem;
  padding: 0.75rem 1rem 0;
  border-bottom: 1px solid #ddd;
}

.tab {
  padding: 0.5rem 1.25rem;
  border: 1px solid #ddd;
  border-bottom: none;
  border-radius: 8px 8px 0 0;
  background: #f4f4f4;
  cursor: pointer;
}

.tab--active {
  background: #fff;
  font-weight: 500;
}

.tab:disabled {
  opacity: 0.4;
  cursor: not-allowed;
}

.tab-content {
  flex: 1;
  overflow-y: auto;
  padding: 1.5rem;
}

.actions {
  display: flex;
  justify-content: flex-end;
  gap: 0.75rem;
  padding: 1rem 1.5rem;
  border-top: 1px solid #ddd;
}

.btn {
  padding: 0.5rem 1.25rem;
  border-radius: 8px;
  border: 1px solid #ccc;
  background: #fff;
  cursor: pointer;
}

.btn--primary {
  background: #333;
  color: #fff;
  border-color: #333;
}
</style>

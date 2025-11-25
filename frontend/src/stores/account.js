import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useAccountStore = defineStore('account', () => {
  const count = ref(0)
  const doubleCount = computed(() => count)
  function increment() {

  }

  return { count, doubleCount, increment }
})

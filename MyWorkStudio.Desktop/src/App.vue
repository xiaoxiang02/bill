<template>
  <div id="app">
	<vue-header v-if="!$route.meta.show"></vue-header>
	<router-view></router-view>
    <vue-footer v-if="!$route.meta.show"></vue-footer>
  </div>
</template>

<script>
import VueHeader from './components/header/header.vue'
import VueFooter from './components/footer/footer.vue'

export default {
  name: 'app',
  components: {
	  VueHeader,
    VueFooter
  }
}
</script>

<style scoped>

</style>

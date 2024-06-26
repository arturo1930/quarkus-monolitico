<template>
  <div>
    <div class="row">
      <div class="col-md-3">
        <span class="hipster img-fluid rounded"></span>
      </div>
      <div class="col-md-9">
        <h1>¡Página de error!</h1>

        <div v-if="errorMessage">
          <div class="alert alert-danger">{{ errorMessage }}</div>
        </div>
        <div v-if="error403" class="alert alert-danger">No tiene permisos para acceder a la página.</div>
        <div v-if="error404" class="alert alert-warning">La página no existe.</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./error.component.ts"></script>

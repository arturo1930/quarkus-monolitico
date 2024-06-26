<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <h1>Restablecer su contraseña</h1>

        <div class="alert alert-warning" v-if="!success">
          <p>Introduzca la dirección de correo electrónico que utilizó para registrarse</p>
        </div>

        <div class="alert alert-success" v-if="success">
          <p>Revise su correo electrónico para obtener más información sobre cómo restablecer su contraseña.</p>
        </div>

        <form v-if="!success" name="form" role="form" v-on:submit.prevent="requestReset()">
          <div class="form-group">
            <label class="form-control-label" for="email">Correo electrónico</label>
            <input
              type="email"
              class="form-control"
              id="email"
              name="email"
              placeholder="Su correo electrónico"
              :class="{ valid: !v$.resetAccount.email.$invalid, invalid: v$.resetAccount.email.$invalid }"
              v-model="v$.resetAccount.email.$model"
              minlength="5"
              maxlength="254"
              email
              required
              data-cy="emailResetPassword"
            />
            <div v-if="v$.resetAccount.email.$anyDirty && v$.resetAccount.email.$invalid">
              <small class="form-text text-danger" v-if="!v$.resetAccount.email.required">Se requiere un correo electrónico.</small>
              <small class="form-text text-danger" v-if="!v$.resetAccount.email.email">Su correo electrónico no es válido.</small>
              <small class="form-text text-danger" v-if="!v$.resetAccount.email.minLength"
                >Se requiere que su correo electrónico tenga por lo menos 5 caracteres</small
              >
              <small class="form-text text-danger" v-if="!v$.resetAccount.email.maxLength"
                >Su correo electrónico no puede tener más de 50 caracteres</small
              >
            </div>
          </div>
          <button type="submit" :disabled="v$.resetAccount.$invalid" class="btn btn-primary" data-cy="submit">
            Restablecer la contraseña
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./reset-password-init.component.ts"></script>

<template>
  <div>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <h1>Restablecer su contraseña</h1>

        <div class="alert alert-danger" v-if="keyMissing">Falta la clave de reinicio.</div>

        <div class="alert alert-danger" v-if="error">
          <p>
            Su contraseña no puede ser restablecida. Recuerde que una solicitud de reinicio de contraseña sólo es válida durante 24 horas.
          </p>
        </div>

        <div class="alert alert-success" v-if="success">
          <span><strong>Su contraseña ha sido restablecida.</strong> Por favor, </span>
          <a class="alert-link" v-on:click="openLogin">iniciar sesión</a>
        </div>
        <div class="alert alert-danger" v-if="doNotMatch">
          <p>¡La contraseña y la confirmación de contraseña no coinciden!</p>
        </div>

        <div class="alert alert-warning" v-if="!success && !keyMissing">
          <p>Elija una contraseña nueva</p>
        </div>

        <div v-if="!keyMissing">
          <form v-if="!success" name="form" role="form" v-on:submit.prevent="finishReset()">
            <div class="form-group">
              <label class="form-control-label" for="newPassword">Nueva contraseña</label>
              <input
                type="password"
                class="form-control"
                id="newPassword"
                name="newPassword"
                placeholder="Nueva contraseña"
                :class="{ valid: !v$.resetAccount.newPassword.$invalid, invalid: v$.resetAccount.newPassword.$invalid }"
                v-model="v$.resetAccount.newPassword.$model"
                minlength="4"
                maxlength="50"
                required
                data-cy="resetPassword"
              />
              <div v-if="v$.resetAccount.newPassword.$anyDirty && v$.resetAccount.newPassword.$invalid">
                <small class="form-text text-danger" v-if="!v$.resetAccount.newPassword.required"
                  >Se requiere que ingrese una contraseña.</small
                >
                <small class="form-text text-danger" v-if="!v$.resetAccount.newPassword.minLength"
                  >Se requiere que su contraseña tenga por lo menos 4 caracteres</small
                >
                <small class="form-text text-danger" v-if="!v$.resetAccount.newPassword.maxLength"
                  >Su contraseña no puede tener más de 50 caracteres</small
                >
              </div>
            </div>
            <div class="form-group">
              <label class="form-control-label" for="confirmPassword">Confirmación de la nueva contraseña</label>
              <input
                type="password"
                class="form-control"
                id="confirmPassword"
                name="confirmPassword"
                :class="{ valid: !v$.resetAccount.confirmPassword.$invalid, invalid: v$.resetAccount.confirmPassword.$invalid }"
                placeholder="Confirmación de la nueva contraseña"
                v-model="v$.resetAccount.confirmPassword.$model"
                minlength="4"
                maxlength="50"
                required
                data-cy="confirmResetPassword"
              />
              <div v-if="v$.resetAccount.confirmPassword.$anyDirty && v$.resetAccount.confirmPassword.$invalid">
                <small class="form-text text-danger" v-if="!v$.resetAccount.confirmPassword.sameAsPassword"
                  >¡La contraseña y la confirmación de contraseña no coinciden!</small
                >
              </div>
            </div>
            <button type="submit" :disabled="v$.resetAccount.$invalid" class="btn btn-primary" data-cy="submit">Guardar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./reset-password-finish.component.ts"></script>

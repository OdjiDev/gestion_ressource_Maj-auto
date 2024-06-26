import { NgModule, ViewChildren } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PageLoginComponent } from './pages/page-login/page-login.component';
import { PageInscriptionComponent } from './pages/page-inscription/page-inscription.component';
import { PageStatistiquesComponent } from './pages/page-statistiques/page-statistiques.component';
import { Router } from '@angular/router';
import { MenuComponent } from './composants/menu/menu.component';
import { CommandComponent } from './command/command.component';
import { CreateUsersComponent } from './pages/page-login/create-users/create-users.component';
import { HomeComponent } from './modules/admin/components/home/home.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { MiseAJourComponent } from './Mise-a-jour/mise-a-jour/mise-a-jour.component';
import { ProgressBarComponent } from './Mise-a-jour/mise-a-jour/progress-bar/progress-bar.component';
import { InscrireComponent } from './login/inscrire/inscrire.component';
import { AdminDashboardComponent } from './modules/admin/components/admin-dashboard/admin-dashboard.component';

const routes: Routes = [

  { path: '', component: AdminDashboardComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
      path: 'admin',
      loadChildren: () =>
            import('./modules/admin/admin.module').then((a) => a.AdminModule),
  },

  {
    path: 'login',
     component: LoginComponent
   },
   {
    path: 'login/inscrire',
     component: InscrireComponent
   },
   {
    path: 'progressbar',
     component: ProgressBarComponent
   },

  {
    path: 'register',
     component: RegisterComponent
    },
    {
      path: 'inscrire',
       component: PageInscriptionComponent
     },
     {
      path: 'singin',
       component: CreateUsersComponent
     },

     //
//Mise a jour routes
     {
      path: 'maj',
       component: MiseAJourComponent
     },{
      path: 'listmiseajour',
       component: MiseAJourComponent
     },{
      path: 'majretro',
       component: MiseAJourComponent
     },

    //PROGRESSE BAR
     {
      path: 'prog',
       component: ProgressBarComponent
     },



        // {
        //   //path: 'updatecategorie/:id',
        //   //component: UpdateCategorieComponent
        //   },



          {
            path: 'command',
            component: CommandComponent
            },
            {
                path: 'stat',
                 component: PageStatistiquesComponent
               },



    //      children: [
    //       {
    //   path: 'statistiques',
    //    component: PageStatistiquesComponent
    //  },


    // //  {
    // //  path: 'menu',
    // //    component:MenuComponent
    // //  }
    //      ]


]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

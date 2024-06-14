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

const routes: Routes = [

  { path: '', component: HomeComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  {
      path: 'admin',
      loadChildren: () =>
            import('./modules/admin/admin.module').then((a) => a.AdminModule),
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

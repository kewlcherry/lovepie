ActionController::Routing::Routes.draw do |map|
  map.resources :organisations
  map.resource :user_session
  map.resource :account, :controller => :users
  map.resources :users
  map.connect ':controller/:action/:id'
  map.connect ':controller/:action/:id.:format'
  map.root :controller => :landing
end

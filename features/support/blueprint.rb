require 'faker'
require 'machinist'
require 'machinist/active_record'

Sham.name { Faker::Name.name }
Sham.email { Faker::Internet.email }
Sham.password { Faker::Name.first_name }
Sham.url { "http://test.com" }

Organisation.blueprint do |o|
  o.name Sham.name
  o.paypal Sham.email
  o.url Sham.url
  o.active false
  o.causes << Cause.make
end

Cause.blueprint do
  name Sham.name
end

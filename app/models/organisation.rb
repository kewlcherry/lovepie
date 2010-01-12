class Organisation < ActiveRecord::Base
  validates_presence_of :name, :paypal, :active
end

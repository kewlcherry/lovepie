class Organisation < ActiveRecord::Base
  acts_as_taggable_on :causes

  validates_presence_of :name, :paypal, :url
  validates_uniqueness_of :name
  validates_format_of :paypal, :with => /\A([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})\Z/i
  validates_format_of :url, :with => /^(http|https):\/\/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(([0-9]{1,5})?\/.*)?$/ix
  validates_inclusion_of :active, :in => [true, false]

  has_many :cause_organisations
  has_many :causes, :through => :cause_organisations
end

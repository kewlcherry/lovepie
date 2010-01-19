class Cause < ActiveRecord::Base

  attr_readonly :popularity_count

  MAX_CAUSES_PER_ORGANISATION = 3

  belongs_to :organisation, :counter_cache => true
  belongs_to :user, :counter_cache => true
  validates_presence_of :name, :organisation_id, :user_id
  validates_uniqueness_of :name, :scope => [:organisation_id, :user_id]

  validate :validate_max

  named_scope :by_popularity, lambda {
    {:select => "*, count(name) as popularity_count", :group => :name, :order => 'popularity_count DESC'}
  }

  def validate_max
    errors.add_to_base("You cannot have more than #{MAX_CAUSES_PER_ORGANISATION} causes per organisation.") unless Cause.count(:conditions => ["user_id = ? AND organisation_id = ?", user, organisation]) < MAX_CAUSES_PER_ORGANISATION
  end
end
